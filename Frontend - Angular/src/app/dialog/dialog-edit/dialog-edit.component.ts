import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Product } from 'src/app/interface/product';
import { ProductService } from 'src/app/product.service';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-edit',
  templateUrl: './dialog-edit.component.html',
  styleUrls: ['./dialog-edit.component.css'],
})
export class DialogEditComponent implements OnInit {
  public mainModel = new Product();
  productForm!: FormGroup;
  public createdBy1!: string;
  product: any;

  constructor(
    private _formBuilder: FormBuilder,
    private _productService: ProductService,
    @Inject(MAT_DIALOG_DATA) public editData: any
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

    if (this.editData) {
      this.productForm.controls['pkgName'].setValue(this.editData.pkgName);
      this.productForm.controls['pkgRental'].setValue(this.editData.pkgRental);
      this.productForm.controls['pkgTotalData'].setValue(
        this.editData.pkgTotalData
      );
      this.productForm.controls['pkgAnyData'].setValue(
        this.editData.pkgAnyData
      );
      this.productForm.controls['pkgNightData'].setValue(
        this.editData.pkgNightData
      );
      this.productForm.controls['pkg4GData'].setValue(this.editData.pkg4GData);
      this.productForm.controls['pkgValidity'].setValue(
        this.editData.pkgValidity
      );
      this.productForm.controls['createdBy'].setValue(this.editData.createdBy);
    }
  }

  editProduct() {
    this.mainModel.id = this.editData.id;
    this.mainModel.pkgName = this.productForm.value.pkgName;
    this.mainModel.pkgRental = this.productForm.value.pkgRental;
    this.mainModel.pkgTotalData = this.productForm.value.pkgTotalData;
    this.mainModel.pkgAnyData = this.productForm.value.pkgAnyData;
    this.mainModel.pkgNightData = this.productForm.value.pkgNightData;
    this.mainModel.pkg4GData = this.productForm.value.pkg4GData;
    this.mainModel.pkgValidity = this.productForm.value.pkgValidity;

    this.createdBy1 = this.productForm.value.createdBy;
    this._productService
      .updateProduct(this.mainModel, this.createdBy1)
      .subscribe((response: any) => {
        this.product = response.body;
        this.productForm.reset();
        console.log(this.product);
      });
  }
}
