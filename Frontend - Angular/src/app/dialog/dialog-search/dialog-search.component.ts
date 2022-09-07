import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Product } from 'src/app/interface/product';
import { ProductService } from 'src/app/product.service';
import { ProductComponent } from 'src/app/product/product.component';

@Component({
  selector: 'app-dialog-search',
  templateUrl: './dialog-search.component.html',
  styleUrls: ['./dialog-search.component.css'],
})
export class DialogSearchComponent implements OnInit {
  public mainModel = new Product();
  productForm!: FormGroup;
  public createdBy1!: string;
  product: any;
  products: any;

  constructor(
    private _formBuilder: FormBuilder,
    private _productService: ProductService
  ) {}

  ngOnInit(): void {
    this.productForm = this._formBuilder.group({
      pkgName: [null],
      pkgRental: [0],
      pkgTotalData: [null],
      pkgAnyData: [null],
      pkgNightData: [null],
      pkg4GData: [null],
      pkgValidity: [null],
      createdBy: [null],
    });
  }

  mainSearch() {
    if (this.productForm.valid) {
      this.mainModel.pkgName = this.productForm.value.pkgName;
      this.mainModel.pkgRental = this.productForm.value.pkgRental;
      this.mainModel.pkgTotalData = this.productForm.value.pkgTotalData;
      this.mainModel.pkgAnyData = this.productForm.value.pkgAnyData;
      this.mainModel.pkgNightData = this.productForm.value.pkgNightData;
      this.mainModel.pkg4GData = this.productForm.value.pkg4GData;
      this.mainModel.pkgValidity = this.productForm.value.pkgValidity;
      this.mainModel.createdBy = this.productForm.value.createdBy;
      console.log(this.mainModel);

      // this._productService.search(this.mainModel).subscribe((response: any) => {
      //   this.products = response.paraList;
      //   this.productForm.reset();
      //   console.log(this.products);
      // });
      this._productService.mainSearch(5, 1);
    }
  }
}
