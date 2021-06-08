import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ToastaService } from 'ngx-toasta';
import { LazyLoadEvent, SelectItem } from 'primeng/api';
import { CategoryService } from '../../services/category.service';
import { ErrorHandlerService } from '../../services/error-handler.service';
import { Category, Transaction, Wallet } from '../../services/models';
import { TransactionFilter, TransactionsService } from '../../services/transactions.service';
import { WalletsService } from '../../services/wallets.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {

  displayCreateTransactionDialog = false;
  displayCreateCategoryDialog = false;
  displayCreateWalletDialog = false;

  transaction: Transaction = new Transaction();
  wallet: Wallet = new Wallet();
  category: Category = new Category();

  transactions: Transaction[] = [];
  categories: SelectItem[] = [];
  wallets: SelectItem[] = [];

  filter: TransactionFilter = new TransactionFilter();
  totalElements = 0;

  @ViewChild('transactionsTable') table;

  constructor(
    private transactionsService: TransactionsService,
    private categoryService: CategoryService,
    private walletService: WalletsService,
    private errorHandler: ErrorHandlerService,
    private toasta: ToastaService
  ) {}

  ngOnInit(): void {
    this.getAllCategories();
    this.getAllWallets();
  }

  loadTransactions(event: LazyLoadEvent) {
    const page: number = event.first / event.rows;
    this.getAllTransactions(page);
  }

  getAllTransactions(page: number = 0): void {
    this.filter.page = page;

    this.transactionsService.getAll(this.filter)
      .then(res => {
        this.transactions = res.content;
        this.totalElements = res.totalElements;
        if (page === 0) {
          this.table.first = 0;
        }
      }).catch(error => {
        this.errorHandler.handle(error);
      });
  }

  save(transactionsForm: NgForm): void {
    this.transactionsService.save(this.transaction)
      .then(res => {
        this.toasta.success('Lançamento salvo com sucesso!');
        this.displayCreateTransactionDialog = false;
        transactionsForm.reset({ value: 0 });

        this.getAllTransactions();
        this.table.first = 0;
      }).catch(error => {
        this.errorHandler.handle(error);
      });
  }

  getAllCategories() {
    this.categoryService.getAll()
    .then(res => {
      this.categories = res.map(c => {
        return { label: c.name, value: c.id };
      });
    }).catch(error => {
      this.errorHandler.handle(error);
    });
  }

  getAllWallets() {
    this.walletService.getAll()
    .then(res => {
      this.wallets = res.map(w => {
        return { label: w.name, value: w.id };
      });
    }).catch(error => {
      this.errorHandler.handle(error);
    });
  }

  saveCategory(categoryForm: NgForm) {
    this.categoryService.save(this.category)
      .then(res => {
        this.toasta.success('Categoria criada com sucesso!');
        this.displayCreateCategoryDialog = false;
        categoryForm.reset();

        this.getAllCategories();
      }).catch(error => {
        this.errorHandler.handle(error);
      });
  }

  saveWallet(walletForm: NgForm) {
    this.walletService.save(this.wallet)
      .then(res => {
        this.toasta.success('Conta / Cartão criado com sucesso!');
        this.displayCreateWalletDialog = false;
        walletForm.reset({ initialBalance: 0 });

        this.getAllWallets();
      }).catch(error => {
        this.errorHandler.handle(error);
      });
  }

  showCreateTransactionDialog(): void {
    this.displayCreateTransactionDialog = true;
  }

  showCreateCategoryDialog() {
    this.displayCreateCategoryDialog = true;
  }

  showCreateWalletDialog() {
    this.displayCreateWalletDialog = true;
  }

  showPaginator(): boolean {
    return this.transactions?.length === 0 ? false : true;
  }

}
