import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { formatDate } from '@angular/common';
import { Client } from '../models/client';
import { Observable, map, catchError, throwError } from 'rxjs';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  baseURL: string = "http://localhost:8080/";
  headers = new HttpHeaders({
    'Content-Type': 'application/json; charset=utf-8'
  });

  constructor(private http: HttpClient) { }

  getClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.baseURL + "api/clients").pipe(
      map( res => {
        let clients = res as Client[]
        return clients.map(client => {
          //client.name = client.name.toLocaleUpperCase()
          if(client.createAt){
            client.createAt = formatDate(client.createAt, 'dd-MM-yyyy', 'en-US')
          }
          return client
        })
      })
    )
  }

  saveClient(client: Client): Observable<Client> {
    return this.http.post<Client>(this.baseURL + "api/clients", client, { "headers": this.headers }).pipe(
      map((res: any) => res.client as Client),
      catchError(
        err => {
          //console.error(err)
          if (err.status === 400) {
            return throwError(() => err)
          }

          Swal.fire({
            icon: 'error',
            title: `${err.error.message}`,
            text: `${err.error.error}` //,
            //footer: '<a href="">Why do I have this issue?</a>'
          })
          return throwError(() => err)
        })
    )
  }

  removeClient(client_id: number): Observable<string> {
    return this.http.delete<string>(this.baseURL + `api/clients/${client_id}`, { "headers": this.headers })
  }

}
