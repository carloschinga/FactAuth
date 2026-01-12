package pe.fact.gestor.comercial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.fact.gestor.comercial.entity.Plan;
import pe.fact.gestor.comercial.repository.PlanRepository;

@Service
public class PlanService {

    @Autowired
    private PlanRepository repository;

    public void crear(Plan plan) {
        // Validación básica
        if(plan.getUsuarioRegistroId() == null) {
            plan.setUsuarioRegistroId(1); // Default: Usuario Administrador (ID 1) si no envían nada
        }

        repository.registrarPlan(
                plan.getNombPlan(),
                plan.getMontPlan(),
                plan.getTipoPlan(),
                plan.getUsuarioRegistroId()
        );
    }
    public java.util.List<Plan> listarTodos() {
        return repository.listarPlanes();
    }
}