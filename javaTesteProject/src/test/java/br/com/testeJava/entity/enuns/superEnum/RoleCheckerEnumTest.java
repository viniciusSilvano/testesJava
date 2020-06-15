package br.com.testeJava.entity.enuns.superEnum;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.testeJava.entity.enuns.superEnum.exception.NotImplementedValidatorException;
import br.com.testeJava.entity.enuns.superEnum.mocks.UmMockQualquer;
import br.com.testeJava.entity.enuns.superEnum.mocks.UsuarioMock;

public class RoleCheckerEnumTest {
	
	private static List<RoleCheckerEnum> enuns;
	@BeforeAll
	public static void initEnuns() {
		enuns = new ArrayList<RoleCheckerEnum>();
		enuns.add(RoleCheckerEnum.HAS_ALL.permissions(RolesEnum.COMUM,RolesEnum.AVANCADO));
		enuns.add(RoleCheckerEnum.HAS_ANY.permissions(RolesEnum.COMUM));
	}
	
	@Test
	public void mustHaveDifferentPermissionsSizePerEnum() {
		Assertions.assertEquals(2, enuns.get(0).getPermissions().length, "A lista deve ser diferente para cada enum");
		Assertions.assertEquals(1, enuns.get(1).getPermissions().length,"A lista deve ser diferente para cada enum");
	}
	
	@Test
	public void mustThrowsExceptionIfNotOverrideExecute() {
		RoleCheckerEnum teste = RoleCheckerEnum.HAS_NOT.permissions(RolesEnum.COMUM);
		Assertions.assertThrows(NotImplementedValidatorException.class, () ->{
			teste.execute(new UsuarioMock());
		});
	}
	
	@Test
	public void mustNotThrowsExceptionIfOverrideExecute() {
		RoleCheckerEnum teste = RoleCheckerEnum.HAS_NOT.permissions(RolesEnum.COMUM);
		Assertions.assertDoesNotThrow(() -> {
			teste.execute(new UsuarioMock(), new UmMockQualquer());
		});
	}
}
