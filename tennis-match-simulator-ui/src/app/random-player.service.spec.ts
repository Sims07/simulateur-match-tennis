import { TestBed } from '@angular/core/testing';

import { RandomPlayerService } from './random-player.service';

describe('RandomPlayerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RandomPlayerService = TestBed.get(RandomPlayerService);
    expect(service).toBeTruthy();
  });
});
