export const environment = {
  production: true,
  apiUrl: 'https://leoncarraro-controlefinanceiro-api.herokuapp.com',

  tokenAllowedDomains: [ new RegExp('leoncarraro-controlefinanceiro-api.herokuapp.com') ],
  tokenDisallowedRoutes: [ new RegExp('\/oauth\/token') ]
};
