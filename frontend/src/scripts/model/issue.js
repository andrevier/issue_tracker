class Issue {
    id;
    name;
    description;
    deadline;
    constructor(
        id,
        name,
        description,
        deadline
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
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

    getDescription(){
        return this.description;
    }

    setDescription(description) {
        this.description = description;
    }

    getDeadline(){
        return this.deadline;
    }

    setDeadline(deadline) {
        this.deadline = deadline;
    }
}

export default Issue;