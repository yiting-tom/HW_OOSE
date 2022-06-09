package models;
import java.util.ArrayList;

public class BoardOfDirectors {
    private ArrayList<BoardMember> boardMembers;
    private Company company;

    public BoardOfDirectors(Company company) {
        this.setCompany(company);
    }

    public int size() {
        return this.boardMembers.size();
    }

    @Override
    public String toString() {
        return "<" + this.getClass().getName() + ": " + this.company.getName() + "> " + " has " + this.boardMembers.size() + " board members";
    }

    public void addMember(BoardMember person) {
        this.boardMembers.add(person);
    }
    public void removeMember(BoardMember person) {
        this.boardMembers.remove(person);
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    public Company getCompany() {
        return this.company;
    }

    public void setBoardMembers(ArrayList<BoardMember> boardMembers) {
        this.boardMembers = boardMembers;
    }
    public ArrayList<BoardMember> getBoardMembers() {
        return this.boardMembers;
    }
}