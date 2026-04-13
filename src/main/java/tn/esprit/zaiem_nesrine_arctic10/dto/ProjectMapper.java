package tn.esprit.zaiem_nesrine_arctic10.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.zaiem_nesrine_arctic10.entities.Project;
@Mapper(componentModel = "spring")
public interface ProjectMapper {
   @Mapping(source = "projectDetails.client", target = "clientName")
   @Mapping(source = "libelle", target = "projectName")
   @Mapping(source = "projectsId", target = "projectid")
   ProjectsDTO toDTO(Project aProject);

}
