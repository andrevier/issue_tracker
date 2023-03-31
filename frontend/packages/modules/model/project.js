class Project {
    
    constructor(name, code, deadline, description) {
        this.name = name;
        this.code = code;
        this.deadline = deadline;
        this.description = description;
    }

    getId() {
        return this.id;
    }
    setId(id) {
        this.id = id;
    }

    getName() {
        return this.name;
    }
    
    setName(name) {
        this.name = name;
    }

    getCode() {
        return this.code;
    }

    setCode(code) {
        this.code = code;
    }

    getDeadline() {
        return this.deadline;
    }

    setDeadline(deadline) {
        this.deadline = deadline;
    }

    getDescription() {
        return this.description;
    }
    
    setDescription(description) {
        this.description = description;
    }
}

export default Project;