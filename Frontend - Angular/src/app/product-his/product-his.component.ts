import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-his',
  templateUrl: './product-his.component.html',
  styleUrls: ['./product-his.component.css'],
})
export class ProductHisComponent implements OnInit {
  public products: any = [];
  public len: any;
  public pageSize: any = 3;
  public pageNumber: any = 0;
  public pe!: PageEvent;
  displayedColumns: string[] = [
    'id',
    'pkgName',
    'pkgRental',
    'pkgTotalData',
    'pkgAnyData',
    'pkgNightData',
    'pkg4GData',
    'pkgValidity',
    'createdBy',
    'crDateTime',
    'uptDateTime',
    'updatedBy',
  ];
  dataSource = new MatTableDataSource<any>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private _productService: ProductService) {}

  ngOnInit(): void {
    this._productService.refreshNeeded$.subscribe(() => {
      this.getAllProducts();
    });

    this.getAllProducts();
  }

  onChangePage(pe: PageEvent) {
    this.pageSize = pe.pageSize;
    this.pageNumber = pe.pageIndex;
    this.getAllProducts();
  }

  getAllProducts() {
    this._productService
      .getProductsHis(this.pageSize, this.pageNumber)
      .subscribe((response: any) => {
        this.len = response.count;
        this.dataSource = new MatTableDataSource(response.paraList);
        this.dataSource.sort = this.sort;
        this.products = response.paraList;
        console.log(this.products);
        console.log(this.len);
      });
  }
}
