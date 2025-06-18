package ProcessEngine.ProcessCore.enteratorModule;

import moduls.Ticket;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.NameValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.PriceValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.TypeValidator;

import java.util.Scanner;

public class EnterTicket implements ComplexEnterator<Ticket> {
    public Ticket enter(Scanner scanner) {
        Ticket ticket = new Ticket(scanner.nextLine());
        ticket.setName(new EnterName().enter(scanner, new NameValidator()));
        ticket.setCoordinates(new EnterCoordinates().enter(scanner));
        ticket.setPrice(new EnterPrice().enter(scanner, new PriceValidator()));
        ticket.setType(new EnterType().enter(scanner, new TypeValidator()));
        ticket.setPerson(new EnterPerson().enter(scanner));
        return ticket;
    }
}
