import { TestBed } from '@angular/core/testing';

import { TennisMatchSimulatorService } from './tennis-match-simulator.service';

describe('TennisMatchSimulatorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TennisMatchSimulatorService = TestBed.get(TennisMatchSimulatorService);
    expect(service).toBeTruthy();
  });
});
