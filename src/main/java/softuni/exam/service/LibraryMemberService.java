package softuni.exam.service;

import softuni.exam.models.entity.LibraryMember;

import java.io.IOException;

// TODO: Implement all methods
public interface LibraryMemberService {

    boolean areImported();

    String readLibraryMembersFileContent() throws IOException;
	
	String importLibraryMembers() throws IOException;

    boolean isExistByGivenId(Long id);


    LibraryMember findById(long id);
}
