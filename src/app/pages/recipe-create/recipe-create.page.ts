import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController } from '@ionic/angular';
import { Recipe } from 'src/app/types/Recipe';

@Component({
  selector: 'app-recipe-create',
  templateUrl: './recipe-create.page.html',
  styleUrls: ['./recipe-create.page.scss'],
})
export class RecipeCreatePage {

  recipe: Recipe = {
    id: 1,
    name: "title",
    subtitle: "subtitle",
    ratings: [
      {
        id: 1,
        rating: 5,
        text: "Super recipe"
      }
    ],
    user: {
      id: 1,
      email: "email@example.com",
    },
    ingredients: [
      {
        id: 1,
        amount: 1,
        text: "ingredient 1",
      }
    ],
    text: "fdsfdsfsd",
    tags: [
      "dsad", "fdfsd"
    ],
    imageBase64: "https://i1.wp.com/meowmeix.com/wp-content/uploads/2017/01/meals.png"
  }

  constructor(
    private alertController: AlertController,
    private router: Router,
  ) { }


  back(): void {
    this.router.navigate(['tabs/my-recipes']);
  }


  save(): void {

  }

  trackByIdx(index: number, obj: any): number {
    return index;
  }

  removeIngredient(index: number): void {
    this.recipe.ingredients.splice(index, 1);
  }

  removeImage(): void {
    this.recipe.imageBase64 = "";
  }

  addIngredient(): void {
    this.recipe.ingredients.push({
      id: this.recipe.ingredients.length + 1,// TODO
      amount: 1,
      text: ""
    });
  }

  public async handleFileInput(event: any): Promise<void> {
    console.log(event);
    const file = event.target.files[0];
    if (!file.name.match(/.(jpg|jpeg|png|gif|webp)$/i)) {
      const alert = await this.alertController.create({ header: 'Chyba!', message: 'Soubor není validní obrázek.', buttons: ['OK'] });
      await alert.present();
      return;
    }
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      this.recipe.imageBase64 = reader.result!.toString();
    };
  }
}
