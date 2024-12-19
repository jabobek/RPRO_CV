import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RecipeCreatePage } from './recipe-create.page';

describe('RecipeCreatePage', () => {
  let component: RecipeCreatePage;
  let fixture: ComponentFixture<RecipeCreatePage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(RecipeCreatePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
