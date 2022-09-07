import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { DialogAddComponent } from '../dialog/dialog-add/dialog-add.component';
import { DialogEditComponent } from '../dialog/dialog-edit/dialog-edit.component';
import { Product } from '../interface/product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit {
  public products: any = [];
  public totCount: number = 0;
  public pageSize: any = 3;
  public pageNumber: any = 0;
  public pe!: PageEvent;
  public mainModel = new Product();
  public productForm!: FormGroup;

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
    'actions',
  ];
  dataSource = new MatTableDataSource<any>();

  @ViewChild('createDialog') callAPIDialog!: TemplateRef<any>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    public dialog: MatDialog,
    private _productService: ProductService,
    private _formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this._productService.refreshNeeded$.subscribe(() => {
      this.mainSearch();
    });

    this.mainSearch();

    this.productForm = this._formBuilder.group({
      pkgName: [null],
      pkgRental: [null],
      pkgTotalData: [null],
      pkgAnyData: [null],
      pkgNightData: [null],
      pkg4GData: [null],
      pkgValidity: [null],
      createdBy: [null],
    });
    console.log(this.productForm.value);
  }

  openDialog() {
    this.dialog.open(this.callAPIDialog, {
      width: '40%',
    });
  }

  openDialogAdd() {
    this.dialog.open(DialogAddComponent, {
      width: '40%',
    });
  }

  openDialogEdit(element: any) {
    this.dialog.open(DialogEditComponent, {
      width: '40%',
      data: element,
    });
  }

  deleteProduct(id: number) {
    this._productService.deleteProduct(id).subscribe((response: any) => {
      console.log(response);
    });
  }

  onChangePage(pe: PageEvent) {
    this.pageSize = pe.pageSize;
    this.pageNumber = pe.pageIndex;
    this.mainSearch();
  }

  addFormGroupDet() {
    this.mainModel.pkgName = this.productForm.value.pkgName;

    if (this.productForm.value.pkgRental == null) this.mainModel.pkgRental = 0;
    else this.mainModel.pkgRental = this.productForm.value.pkgRental;

    this.mainModel.pkgTotalData = this.productForm.value.pkgTotalData;
    this.mainModel.pkgAnyData = this.productForm.value.pkgAnyData;
    this.mainModel.pkgNightData = this.productForm.value.pkgNightData;
    this.mainModel.pkg4GData = this.productForm.value.pkg4GData;
    this.mainModel.pkgValidity = this.productForm.value.pkgValidity;
    this.mainModel.createdBy = this.productForm.value.createdBy;
    console.log(this.mainModel);
    this.mainSearch();
  }

  mainSearch() {
    if (Object.keys(this.mainModel).length === 0) {
      console.log('Object is empty');

      this._productService
        .mainSearch(this.pageSize, this.pageNumber)
        .subscribe((response: any) => {
          this.totCount = response.count;
          this.dataSource = new MatTableDataSource(response.paraList);
          this.dataSource.sort = this.sort;
          this.products = response.paraList;
          this.productForm.reset();
        });
    } else {
      this._productService
        .mainSearch(this.pageSize, this.pageNumber, this.mainModel)
        .subscribe((response: any) => {
          if (response.count == 0) console.log('No Matching Values Found');
          this.totCount = response.count;
          this.dataSource = new MatTableDataSource(response.paraList);
          this.dataSource.sort = this.sort;
          this.products = response.paraList;
          this.productForm.reset();
        });
    }
  }
}
