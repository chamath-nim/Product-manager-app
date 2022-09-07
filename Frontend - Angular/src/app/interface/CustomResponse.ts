import { Product } from './product';
import { ResponseHeader } from './responseHeader';

export class CustomResponse {
  responseHeader?: ResponseHeader;
  paraList?: Product[];
  body?: Product;
  count?: number;
}
