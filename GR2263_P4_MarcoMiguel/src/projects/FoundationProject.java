package projects;

import citizenship.Foundation;

public class FoundationProject extends Project {
    private double estimatedBudget;
    private double percentage;
    
    public FoundationProject(String title, String description, Foundation proposer, double estimatedBudget, double percentage) {
        super(title, description, proposer);
        if (estimatedBudget <= 0) {
            throw new IllegalArgumentException("The estimated budget must be greater than zero.");
        }
        if (percentage < 1 || percentage > 100) {
            throw new IllegalArgumentException("The percentage must be between 1 and 100.");
        }
        this.estimatedBudget = estimatedBudget;
        this.percentage = percentage;
    }
    
    public double getEstimatedBudget() {
        return estimatedBudget;
    }
    
    public double getPercentage() {
        return percentage;
    }
    
    @Override
    public String toString() {
        // Ejemplo: 
        // "1: Gastemos menos agua. Proponente: Fundación Canal CIF (A1234567B) <fundación>. Presupuesto: 1000000.0€. Porcentaje: 80.0% /proyecto de fundación/"
        return code + ": " + title + ". Proponente: " + proposer.toString() + 
               ". Presupuesto: " + estimatedBudget + "€. Porcentaje: " + percentage + "% /proyecto de fundación/";
    }
}
