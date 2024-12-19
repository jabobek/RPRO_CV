import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Recipe } from 'src/app/types/Recipe';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.page.html',
  styleUrls: ['./recipe.page.scss'],
})
export class RecipePage implements OnInit {

  @Input() recipe: Recipe | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
  ) { }


  ngOnInit() {
    const recipeData = this.route.snapshot.paramMap.get('recipeData');
    if (recipeData) {
      this.recipe = JSON.parse(recipeData);
    }
  }

  back():void{
    this.router.navigate(['tabs/recipes']);
  }
}
