package Composite;


import java.util.List;
import Visitor.Visitor;

public interface UnitComponent {
    String getName();

    void accept(Visitor v);

}