import { IonicModule } from '@ionic/angular';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MyRecipesPage } from './my-recipes.page';

import { MyRecipesPageRoutingModule } from './my-recipes-routing.module';
import { RecipeItemComponentModule } from 'src/app/components/recipe-item/recipe-item.module';

@NgModule({
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    MyRecipesPageRoutingModule,
    RecipeItemComponentModule
  ],
  declarations: [MyRecipesPage]
})
export class MyRecipesPageModule {}
