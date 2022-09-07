import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Product } from 'src/app/interface/product';
import { ProductService } from 'src/app/product.service';

@Component({
  selector: 'app-dialog-add',
  templateUrl: './dialog-add.component.html',
  styleUrls: ['./dialog-add.component.css'],
})
export class DialogAddComponent implements OnInit {
  public mainModel = new Product();
  productForm!: FormGroup;
  public createdBy1!: string;
  product: any;

  constructor(
    private _formBuilder: FormBuilder,
    private _productService: ProductService
  ) {}

  ngOnInit(): void {
    this.productForm = this._formBuilder.group({
      pkgName: [null, Validators.required],
      pkgRental: [null, Validators.required],
      pkgTotalData: [null, Validators.required],
      pkgAnyData: [null],
      pkgNightData: [null],
      pkg4GData: [null],
      pkgValidity: [null, Validators.required],
      createdBy: [null, Validators.required],
    });
  }

  addProduct() {
    if (this.productForm.valid) {
      this.createdBy1 = this.productForm.value.createdBy;
      this.mainModel.pkgName = this.productForm.value.pkgName;
      this.mainModel.pkgRental = this.productForm.value.pkgRental;
      this.mainModel.pkgTotalData = this.productForm.value.pkgTotalData;
      this.mainModel.pkgAnyData = this.productForm.value.pkgAnyData;
      this.mainModel.pkgNightData = this.productForm.value.pkgNightData;
      this.mainModel.pkg4GData = this.productForm.value.pkg4GData;
      this.mainModel.pkgValidity = this.productForm.value.pkgValidity;
      this._productService
        .addProduct(this.mainModel, this.createdBy1)
        .subscribe((response: any) => {
          this.product = response.body;
          this.productForm.reset();
          console.log(this.product);
        });
    }
  }
}
