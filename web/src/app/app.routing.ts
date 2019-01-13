import {Routes, RouterModule} from '@angular/router';

import { FormFlowerComponent } from './module/flower/form-flower/form-flower.component';

const appRoutes: Routes = [
    { path : '', component : FormFlowerComponent },
    { path : 'flower', component : FormFlowerComponent },
    { path : '**', redirectTo : '' }
];

export const routing = RouterModule.forRoot(appRoutes);