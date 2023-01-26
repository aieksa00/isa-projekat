package isaapp.g3malt.repository;

import isaapp.g3malt.model.Questionnaire;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Integer> {
	public List<Questionnaire> findAllByUserId(Integer userId);
}
