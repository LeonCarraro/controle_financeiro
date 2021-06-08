export class Transaction {
    description: string;
    walletId: number;
    categoryId: number;
    value: number = 0;
    insertionDate: Date;
}

export class Wallet {
    name: string;
    initialBalance: number = 0;
}

export class Category {
    name: string;
}

export class SignupRequest {
    username: string;
    password: string;
}
