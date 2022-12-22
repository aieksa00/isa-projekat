package isaapp.g3malt.controller;

import isaapp.g3malt.dto.BloodBankDto;
import isaapp.g3malt.dto.FutureAppointmentDto;
import isaapp.g3malt.dto.QuestionnaireDTO;
import isaapp.g3malt.dto.StaffDto;
import isaapp.g3malt.model.Appointment;
import isaapp.g3malt.model.BloodBank;
import isaapp.g3malt.model.Questionnaire;
import isaapp.g3malt.model.User;
import isaapp.g3malt.services.QuestionnaireService;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questionnaireController")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;
    
	private ModelMapper modelMapper = new ModelMapper();

    
    @GetMapping(value = "GetByCustomerId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('STAFF')")
	public ResponseEntity<QuestionnaireDTO> GetQuestionnaireByCustomerId(@PathVariable Integer id) {
		
		Questionnaire questionnaire = questionnaireService.findByUserId(id);

		if (questionnaire == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		QuestionnaireDTO dto = modelMapper.map(questionnaire, QuestionnaireDTO.class);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

}
