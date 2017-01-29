# Eat Healthy

1. What is the proposed name for your Web application?
    - Eat Healthy

2. Who is the target audience for your Web application?
    - Users who change their diet and eating behavior, and looking for healthy recipes.

3. What problem is it intended to solve for the target audience?
    - People might not have enough knowledge to identify the healthy recipe. Eating healthfully, doesn’t necessarily mean you have to chow down on salads all day long. There are plenty of healthy, hearty and tasty dishes that have positive effects on overall health. I am going to provide healthy recipes with their nutrition for free, and let users to review these recipes and bookmark their favorite dishes.

4. How will it meet the minimum project requirements?
    - The content, features, and services of my site will be available free of charge. Therefore, users do not require a sign-up/login to access the content. However, it is recommended in order to take advantage of all offered features. In order to finding an easy access to the site, I will support OAuth 2.0 for authentication. There are two user roles available with my site: administrator and member. Administrator role would be typically giving only one user who has access to all administrative options and features, including managing (CRUD processes on) all users’ profiles, recipes, etc. Member role is defined when a normal user signs up. Member is able to share recipes with others, give a feedback about recipes, and keep record of favorite dishes. All content will be stored into my database, including users’ credentials. All public data will be accessible through RESTful services that use data in both JSON and XML formats.

5. Why is your proposed Web application unique or creative beyond simply meeting the minimum requirements?
    - Beside the fact that my site will let their members to keep their preferred dishes in one place, easy to access from everywhere, at any time, there are some other remarkable features. To begin with, I will allow the users to rate recipes and provide feedback for those recipes giving a try. Therefore, members can benefit from reading those feedbacks in order to decide which recipe should they try.  In addition, adaptive recommender systems are becoming more common-place across various domains, and thus exhibiting recipes suggestions to users based on their preferences and feedback can make their visiting extraordinary.

## Build status

[![Build Status](https://travis-ci.org/infsci2560sp17/full-stack-web-MHarbi.svg?branch=master)](https://travis-ci.org/infsci2560sp17/full-stack-web-MHarbi)

## Web Site 

[Website Name](https://immense-garden-72100.herokuapp.com)

![](https://www.gravatar.com/userimage/37620264/29a3514e471644dbba82aef0ac1b72c1)

## Key Features

TODO : Please list key features of your project.

* Key Feature 1
* Key Feature 2
* Key Feature N

## Project Details

### Landing Page

TODO : please provide a description of your landing page inluding a screen shot ![](https://.../image.JPG)

### User Input Form

TODO : please provide a description of at least 1 user input form including a screen shot ![](https://.../image.jpg)

## API

TODO : please provide a description of at least 1 API including a sample of request data and response data in both XML and JSON format.

### API Method 1

    POST photos/:id/tags

#### Parameters

- **id** _(required)_ — The Photo ID to add tags for.
- **tags** _(required)_ — Comma separated tags.

#### Response

A JSON or XMLobject containing the PhotoID and list of tags accepted.

#### Errors

All known errors cause the resource to return HTTP error code header together with a JSON array containing at least 'status' and 'error' keys describing the source of error.

- **404 Not Found** — The photo was not found.

#### Example

##### Request

    POST /v1/photos/123456/tags

##### Body

    tags=cute,puppy


##### JSON Response

```json
{
    "photoId": 123456,
    "tags": ["cute", "puppy"]
}
```

##### XML Response

```xml
<?xml version="1.0" encoding="UTF-8"?>
<PhotoTags>
    <photoId>123456</PhotoId>
        <tags>
            <tag>cute</tag>
            <tag>puppy</tag>
        </tags>
</PhotoTags>
```

## Technologies Used

TODO : List all technologies used in your project

- [Spring Boot](https://projects.spring.io/spring-boot/) - Takes an opinionated view of building production-ready Spring applications.
- [Thymleaf](http://www.thymeleaf.org/) - Thymeleaf is a modern server-side Java template engine for both web and standalone environments.
- [Maven](https://maven.apache.org/) - Apache Maven is a software project management and comprehension tool.