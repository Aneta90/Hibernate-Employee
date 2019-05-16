package pl.sda.hibernate.domain;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.testng.annotations.Test;
import pl.sda.hibernate.pl.sda.hibernate.util.SessionUtil;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class CandidatesFilterTest {


    @Test
    public void testFilters() {

        Candidates candidates = new Candidates();
        candidates.setId(1L);
        candidates.setName("Anetaa");
        candidates.setSurname("Sun");
        candidates.setJobApplicationStatus("active");
        candidates.setPreviousExperienceInYears(6L);

        Candidates candidates1 = new Candidates();
                candidates1.setId(2L);
                candidates1.setName("Kate");
                candidates1.setSurname("Ka");
                candidates1.setJobApplicationStatus("active");
                candidates1.setPreviousExperienceInYears(3L);

        Candidates candidates3 = new Candidates();
                candidates3.setId(3L);
                candidates3.setName("Tom");
                candidates3.setSurname("Tomson");
                candidates3.setJobApplicationStatus("non-active");
                candidates3.setPreviousExperienceInYears(10L);

        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(candidates);
            session.save(candidates1);
            session.save(candidates3);
            transaction.commit();
            assertNotNull(candidates);
            assertNotNull(candidates1);
            assertNotNull(candidates3);
        }

        try (Session session = SessionUtil.getSession()) {
            session.beginTransaction();
            Query <Candidates> query = session.createQuery("from Candidates",Candidates.class);
            session.enableFilter("byStatus").setParameter("status", "active");
            session.enableFilter("byExperience").setParameter("experience",5L);
            List<Candidates>candidatesList= query.list();
            for(Candidates c: candidatesList){
                System.out.println(c.getName());
            }
            assertEquals(candidatesList.size(),1);
            session.close();
        }
        }
    }

