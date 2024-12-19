import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { RecipeCreatePageRoutingModule } from './recipe-create-routing.module';

import { RecipeCreatePage } from './recipe-create.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RecipeCreatePageRoutingModule
  ],
  declarations: [RecipeCreatePage]
})
export class RecipeCreatePageModule {}
