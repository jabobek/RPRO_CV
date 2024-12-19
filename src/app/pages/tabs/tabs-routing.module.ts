import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TabsPage } from './tabs.page';

const routes: Routes = [
  {
    path: 'tabs',
    component: TabsPage,
    children: [
      {
        path: 'recipes',
        loadChildren: () => import('../recipes/recipes.module').then(m => m.RecipesPageModule)
      },
      {
        path: 'my-recipes',
        loadChildren: () => import('../my-recipes/my-recipes.module').then(m => m.MyRecipesPageModule)
      },
      {
        path: 'account',
        loadChildren: () => import('../account/account.module').then(m => m.Tab3PageModule)
      },
      {
        path: '',
        redirectTo: '/tabs/tab1',
        pathMatch: 'full'
      }
    ]
  },
  {
    path: '',
    redirectTo: '/tabs/recipes',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
})
export class TabsPageRoutingModule {}
