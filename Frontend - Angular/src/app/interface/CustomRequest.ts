import { Product } from './product';
import { RequestHeader } from './requestHeader';

export class CustomRequest {
  requestHeader?: RequestHeader;
  paraList?: Product[];
  body?: Product;
}
