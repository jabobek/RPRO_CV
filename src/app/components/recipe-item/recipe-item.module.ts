import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { RecipeItemComponent } from './recipe-item.component';

@NgModule({
  declarations: [RecipeItemComponent],
  exports: [RecipeItemComponent],
  imports: [
    CommonModule,
    FormsModule,
    IonicModule
  ]
})
export class RecipeItemComponentModule { }
