import { Component, OnInit } from '@angular/core';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Client } from '../models/client';
import { ClientService } from '../services/client.service';
import Swal from 'sweetalert2';

@Component({
	selector: 'app-client-list',
	templateUrl: './client-list.component.html',
	styleUrls: ['./client-list.component.css'],
	// add NgbModalConfig and NgbModal to the component providers
	providers: [NgbModalConfig, NgbModal],
})
export class ClientListComponent implements OnInit {
	client: Client
	clients: Client[] = [];
	errors: string[] = [];

	constructor(private clientService: ClientService, config: NgbModalConfig, private modalService: NgbModal) {
		// customize default values of modals used by this component tree
		config.backdrop = 'static';
		config.keyboard = false;
		this.errors = []
		this.client = {
			id: 0,
			name: '',
			lastName: '',
			email: ''
		}
	}

	ngOnInit(): void {
		this.getClients()
	}

	resetFormClient(): void {
		this.errors = []
		this.client = {
			id: 0,
			name: '',
			lastName: '',
			email: ''
		}
	}

	open(content: any, mClient?: Client) {
		this.modalService.open(content, { size: 'lg' });
		this.resetFormClient()
		if (mClient) {
			this.client.id = mClient.id;
			this.client.name = mClient.name;
			this.client.lastName = mClient.lastName;
			this.client.email = mClient.email;
		}
	}

	getClients(): void {
		this.clientService.getClients().subscribe(res => {
			this.clients = res
		})
	}

	submitForm(): void {
		//console.log(this.client)
		this.clientService.saveClient(this.client).subscribe({
			next: (res) => {
				//console.log(res)
				this.modalService.dismissAll()
				this.resetFormClient()
				this.getClients()
				Swal.fire({
					position: 'top-end',
					icon: 'success',
					title: `¡Cliente ${res.name} guardado con éxito!`,
					showConfirmButton: false,
					timer: 1500
				})
			},
			error: (e) => {
				//console.log(`Frm`)
				//console.log(e)
				this.errors = e.error.errors as string[]
				console.error(e.error.errors)
			}
		})
	}

	removeClient(client: Client): void {
		Swal.fire({
			title: `¿Estás seguro de eliminar el cliente: ${client.name}?`,
			text: "¡No podrás revertir esto!",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: '¡Sí, bórralo!'
		}).then((result) => {
			if (result.isConfirmed) {
				this.clientService.removeClient(client.id).subscribe(res => {
					//console.log(res)
					this.getClients()
					Swal.fire(
						'¡Eliminado!',
						`El cliente ${client.name} ha sido eliminado.`,
						'success'
					)
				})
			}
		})
	}

}
