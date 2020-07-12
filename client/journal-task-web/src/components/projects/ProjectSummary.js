import React from 'react';
import moment from 'moment';

const ProjectSummary = ({project}) => {
    if (!project) return (<div className="container center">
    <p>Loading projects...</p>
  </div>);
    return (
        <div className = "card z-depth-0 project-summary">
            <div className = "card-content grey-text text-darken-3">
                <span className = "card-title">{project.title}</span>
                <p>By {project.authorFirstName} {project.authorLastName}</p>
                <p className = "grey-text">{project.createdAt && moment(project.createdAt.toDate()).calendar()}</p>
            </div>
        </div>
    );

}

export default ProjectSummary;