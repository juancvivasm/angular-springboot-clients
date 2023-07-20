export class Client {
    id: number;
    name: string;
    lastName: string;
    createAt?: string;
    email: string;

    constructor() {
        this.id = 1;
        this.name = '';
        this.lastName = '';
        this.createAt = '';
        this.email= '';
    }
}
