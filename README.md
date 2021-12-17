
<div align="center">
    <img src="src/main/resources/static/Attachments/deadlineLogo_LowRes_transparent.png" alt="ManageMan" width="120" height="130">
    <h1 align="center">Project ManageMan 📆</h1>

![GitHub repo size](https://img.shields.io/github/repo-size/RandomHat/ProjectManageMan?style=flat-square&logo=appveyor)
![GitHub contributors](https://img.shields.io/github/contributors/RandomHat/ProjectManageMan?style=flat-square&logo=appveyor)
![GitHub stars](https://img.shields.io/github/stars/RandomHat/ProjectManageMan?style=flat-square&logo=appveyor)
![GitHub licence](https://img.shields.io/github/license/RandomHat/ProjectManageMan?style=flat-square&logo=appveyor)
</div>

## Project ManageMan is live

Try the ManageMan demo program here:
https://manageman.herokuapp.com/

### Test-users

| Username | Password |
|----------|----------|
| Admin    | 1234     |
| User     | 1234     |

## Get Started 🎯


### Running it locally 🏠

### Prerequisites

* Java 11 or above
* MySQL 8

To run the application locally, clone the project to a workspace.

```sh
   git clone https://github.com/RandomHat/ProjectManageMan.git
```

Open project in an IDE of your choice (works flawlessly in IntelliJ) \
Create and connect the Database built from the MySQL script. \
Run spring application, and connect to localhost:8080

## Deploy Application to Heroku ☁

### Host your MySQL database 

1. Sign up for a MySQL database hosting service. We recommend Jaws_DB free option.
2. Create a database using the accompanying MySQL script. 
3. Format your database credentials in the following form:

```
mysql://{username}:{password}@{Host URL}:{port}/{schema}
```

And link it to the environment variable `JAWSDB_URL`

### Heroku Host Guide

1. Host MySQL database (see above)
2. Fork ManageMan repository 
3. Create app on Heroku
4. Connect Github repository
5. Set environment variable `JAWSDB_URL` to format as described above.
6. (optional) Enable automatic redeployment on commit to master branch
7. Host 😎

## ✨ Contributors ✨

* [@BenAtic-KEA](https://github.com/BenAtic-KEA)
* [@FrederikWVB](https://github.com/FrederikWVB)
* [@RandomHat](https://github.com/RandomHat)

