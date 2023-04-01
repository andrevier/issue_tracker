import Issue from './model/issue.js';
import Project from './model/project.js';
import User from './model/user.js';
import fakeUser from './fakeUser.js';
import TableProjects from './view/tableProjects.js';
// import {v4 as uuidv4} from '../node_modules/uuid';

class AppController {
    
    user; // User class object.
    tableProjects;

    constructor() {
        // Get the User object from the local storage.
        this.user = new User(
            window.localStorage.getItem('user').id, 
            window.localStorage.getItem('user').name, 
            window.localStorage.getItem('user').email, 
            window.localStorage.getItem('user').password, 
            window.localStorage.getItem('user').projects
        );
        this.tableProjects = new TableProjects(
            window.localStorage.getItem('user').projects
        );
    }

    checkUserData(email, password) {
        // Send a request to the server to check the email and password.
        if (email === fakeUser.email && password === fakeUser.password) {
            this.user = new User(
                fakeUser.id, 
                fakeUser.name, 
                fakeUser.email, 
                fakeUser.password, 
                fakeUser.projects
            );
            return true;
        } else {
            return false;
        }    
    }

    userLogin(email, password) {
        // Check if the user exists in the database.
        if (this.checkUserData(email, password)) {

            window.localStorage.setItem('user', this.user);
            
            window.location.href= './projects.html';
        } else {
            console.log('wrong user name or email.');
        }
    }

    userLogout() {
        //Logout logic
        window.location.href='./login.html'
    }

    userSignup(name, email, password) {
        // Signup logic.
        this.user = {
            id: 1,
            name: name,
            email: email,
            password: password,
            projects: []
        }

        window.localStorage.setItem('user', this.user);

        window.location.href='./projects.html';
    }

    getUserProjects() {
        return this.user.getProjects();
    }

    addUserProject(projectName, projectCode, projectDeadline, projectDescription) {
        this.user.addProject(
            projectName,
            projectCode,
            projectDeadline, 
            projectDescription
        );
        
        window.localStorage.setItem('user', this.user);

        window.location.href='./projects.html';
    }

    updateTableProjects(projects) {
        this.user.getProjects(projects);
        console.log(projects);
        //this.tableProjects.updateTable(projects);
    }

}

export default AppController;