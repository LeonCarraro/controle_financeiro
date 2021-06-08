import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { ErrorHandlerService } from 'src/app/services/error-handler.service';
import { GeneralStatistic, StatisticByCategories } from './home.models';
import { HomeService } from './home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  generalStatistics: GeneralStatistic;
  positiveChartData: any;
  negativeChartData: any;
  chartOptions: any;

  constructor(
    public authService: AuthService,
    private homeService: HomeService,
    private errorHandler: ErrorHandlerService
  ) {}

  ngOnInit(): void {
    this.getGeneralStatistics();
  }

  getGeneralStatistics() {
    this.homeService.getGeneralStatistics()
      .then(res => {
        this.generalStatistics = res;
        this.createCharts();
      }).catch(error => {
        this.errorHandler.handle(error);
      });
  }

  createCharts() {
    const positiveNames: string[] = [];
    const positiveValues: number[] = [];
    const negativeNames: string[] = [];
    const negativeValues: number[] = [];

    const positiveArray: StatisticByCategories[] = this.generalStatistics.positiveStatisticByCategory;
    positiveArray.forEach(item => {
      positiveNames.push(item.categoryName);
      positiveValues.push(item.value);
    });
    const negativeArray: StatisticByCategories[] = this.generalStatistics.negativeStatisticByCategory;
    negativeArray.forEach(item => {
      negativeNames.push(item.categoryName);
      negativeValues.push(item.value);
    });

    this.positiveChartData = {
      labels: positiveNames,
      datasets: [
        {
          data: positiveValues,
          backgroundColor: [
            '#FF6384',
            '#36A2EB',
            '#FFCE56',
            '#BB4202',
            '#AF8425'
          ]
        }
      ]
    };

    this.negativeChartData = {
      labels: negativeNames,
      datasets: [
        {
          data: negativeValues,
          backgroundColor: [
            '#5f3841',
            '#6551b8',
            '#a98f5c',
            '#644839',
            '#AF8425'
          ]
        }
      ]
    };

    this.chartOptions = {
      title: {
          display: false,
          text: 'Categorias',
          fontSize: 16
      },
      legend: {
          position: 'bottom',
          labels: {
            usePointStyle: true,
            fontSize: 14,
            fontFamily: 'Nunito',
            padding: 30
          }
      }
    };
  }

}
