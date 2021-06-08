export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080',

  tokenAllowedDomains: [ new RegExp('localhost:8080') ],
  tokenDisallowedRoutes: [ new RegExp('\/oauth\/token') ]
};
