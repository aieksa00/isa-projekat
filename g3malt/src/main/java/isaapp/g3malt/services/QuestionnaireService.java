package isaapp.g3malt.services;

import isaapp.g3malt.model.Questionnaire;
import isaapp.g3malt.repository.QuestionnaireRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionnaireService implements IService<Questionnaire, Integer>{
    @Autowired
    private QuestionnaireRepository questionnaireRepository;


    @Override
    public Questionnaire save(Questionnaire entity) {
        return questionnaireRepository.save(entity);
    }

    @Override
    public Questionnaire edit(Questionnaire entity) {
        return null;
    }

    @Override
    public Questionnaire findById(Integer id) {
        return questionnaireRepository.findById(id).orElseGet(null);
    }

    @Override
    public Iterable<Questionnaire> findAll() {
        return questionnaireRepository.findAll();
    }

    @Override
    public Iterable<Questionnaire> findAllById(Iterable<Integer> ids) {
        return questionnaireRepository.findAllById(ids);
    }

    @Override
    public void deleteById(Integer id) {
        questionnaireRepository.deleteById(id);
    }
    
    public Questionnaire findByUserId(Integer id)
    {
    	List<Questionnaire> allQuestionnaires = questionnaireRepository.findAllByUserId(id);
    	if(allQuestionnaires.size() == 1)
    	{
    		return allQuestionnaires.get(0);
    	}
    	
    	int maxId =  allQuestionnaires.get(0).getId();
    	Questionnaire newestQ = allQuestionnaires.get(0);
    	for(Questionnaire q : allQuestionnaires)
    	{
    		if(q.getId() > maxId)
    		{
    			maxId = q.getId();
    			newestQ = q;
    		}
    	}
        return newestQ;
    }
}
