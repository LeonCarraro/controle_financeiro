export interface WalletResponse {
    id: number;
    name: string;
    balance: number;
}

export interface StatisticByCategories {
    categoryName: string;
    value: number;
}

export interface GeneralStatistic {
    username: string;
    wallets: WalletResponse[];
    totalBalance: number;
    positiveStatisticByCategory: StatisticByCategories[];
    negativeStatisticByCategory: StatisticByCategories[];
}
