package pl.sda.hibernate.domain;

import com.sun.org.apache.xpath.internal.operations.String;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.testng.annotations.Test;
import pl.sda.hibernate.pl.sda.hibernate.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class CandidatesFilterTest {


    @Test
    public void testFilters() {

        Candidates candidates = Candidates.builder()
                .id(1L)
                .name("Anetaa")
                .surname("Sun")
                .jobApplicationStatus("active")
                .previousExperienceInYears(6L)
                .build();

        Candidates candidates1 = Candidates.builder()
                .id(2L)
                .name("Kate")
                .surname("Moon")
                .jobApplicationStatus("active")
                .previousExperienceInYears(3L)
                .build();

        Candidates candidates3 = Candidates.builder()
                .id(3L)
                .name("Tom")
                .surname("Tomson")
                .jobApplicationStatus("non-active")
                .previousExperienceInYears(10L)
                .build();

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

