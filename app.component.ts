import { Component } from '@angular/core';

@Component({
  selector: 'my-app',
  template: `
  <h1>Hello {{name}}</h1>
  <p><strong>E-mail:</strong> {{email}}</p>
  <p><strong>address:</strong> {{address.address}}, {{address.dpt}}-{{address.city}}</p>
  `
})
export class AppComponent { 
  name = 'Angular'; 
  email = 'johndoe@gmail.com';
  address = {
    dpt : 'Antioquia',
    city : 'Medellin',
    address : 'Cl 50 N 51-65'
 };
  
}


/*
Copyright 2016 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/