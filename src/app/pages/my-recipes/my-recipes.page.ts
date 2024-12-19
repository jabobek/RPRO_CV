import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Recipe } from 'src/app/types/Recipe';

@Component({
  selector: 'app-my-recipes',
  templateUrl: 'my-recipes.page.html',
  styleUrls: ['my-recipes.page.scss'],
  standalone: false,
})
export class MyRecipesPage {

  recipes: Recipe[] = [
    {
      id: 1,
      name: "My recipe",
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
  ]

  constructor(
    private router: Router,
  ) { }

  addNew(): void {
    this.router.navigate(['recipe-create']);
  }

}
