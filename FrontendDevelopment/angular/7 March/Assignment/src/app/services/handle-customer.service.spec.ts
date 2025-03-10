import { TestBed } from '@angular/core/testing';

import { HandleCustomerService } from './handle-customer.service';

describe('HandleCustomerService', () => {
  let service: HandleCustomerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HandleCustomerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
