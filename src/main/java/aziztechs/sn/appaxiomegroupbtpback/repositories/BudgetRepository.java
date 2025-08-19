package aziztechs.sn.appaxiomegroupbtpback.repositories;

import aziztechs.sn.appaxiomegroupbtpback.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    @Query("SELECT b FROM Budget b WHERE b.montantEngage > b.montantPrevu * 0.9")
    List<Budget> findBudgetsApproachingLimit();

    @Query("SELECT SUM(b.montantPrevu) FROM Budget b")
    Double getTotalBudgetPrevu();

    @Query("SELECT SUM(b.montantEngage) FROM Budget b")
    Double getTotalBudgetEngage();
}
