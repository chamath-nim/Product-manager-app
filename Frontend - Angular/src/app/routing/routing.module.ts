import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { ProductComponent } from '../product/product.component';
import { ProductHisComponent } from '../product-his/product-his.component';

const routes: Routes = [
  { path: '', component: ProductComponent },
  { path: 'products-his', component: ProductHisComponent },
];

@NgModule({
  declarations: [],
  imports: [CommonModule, RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class RoutingModule {}
export const routingComponents = [ProductComponent, ProductHisComponent];
