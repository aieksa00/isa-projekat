package isaapp.g3malt.repository;

import isaapp.g3malt.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Integer> {
	public Questionnaire findByUserId(Integer userId);
}
