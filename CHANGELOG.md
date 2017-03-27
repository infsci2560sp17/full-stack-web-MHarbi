# Change Log
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/).

## [Unreleased]
### Added
- Fix an error in adding comments
- Change the website theme
- Build a template and uniform the header and footer for all pages

## [v1.4] - 2017-03-25
### Added
- Created Entities
    - User
    - Ingredient
    - Nutrition
    - Comment
- Created a Repository
    - UserRepository
    - IngredientRepository
    - NutritionRepository
    - CommentRepository
- Created a RESTful web service and return JSON
    - UsersService
    - IngredientsService
    - NutritionsService
- Created a MVC Controller
    - UserController
    - CommentController
- Created/updated a MVC Page
    - ajax
        - profile.html
    - recipe
        - create.html
        - index.html
        - update.html
        - view.html
    - user
        - index.html
        - update.html
        - view.html
    - hello.html
    - home.html
    - login.html
    - registration.html
- Included in README.md
    - Key Features

## [v1.3] - 2017-02-18
### Added
- Created Entities/sub-Entities
    - Recipe
    - Ingredient
    - Nutrition
- Created a Repository
    - RecipeRepository
- Created a RESTful web service and return JSON
    - RecipesService
- Created a MVC Controller and implement get and post methods
    - RecipesController
- Created a MVC Page and wire it to the Repository
    - recipes.html
- Included in README.md
    - Key Features

## [v1.2] - 2017-01-28
### Added
- Answered in README.md
    - What is the proposed name for your Web application?
    - Who is the target audience for your Web application?
    - What problem is it intended to solve for the target audience?
    - How will it meet the minimum project requirements?
    - Why is your proposed Web application unique or creative beyond simply meeting the minimum requirements?

## [v1.1] - 2017-01-21
### Added
- Cloned, configured, and compiled the scaffold project.
- Configured developer workspace.
- Deployed to Heroku.

[Unreleased]: https://github.com/infsci2560sp17/full-stack-web-MHarbi/compare/v1.2...HEAD
[v1.4]: https://github.com/infsci2560sp17/full-stack-web-MHarbi/compare/v1.3...v1.4
[v1.3]: https://github.com/infsci2560sp17/full-stack-web-MHarbi/compare/v1.2...v1.3
[v1.2]: https://github.com/infsci2560sp17/full-stack-web-MHarbi/compare/v1.1...v1.2
[v1.1]: https://github.com/infsci2560sp17/full-stack-web-MHarbi/compare/...v1.1