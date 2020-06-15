package br.com.testeJava.entity.enuns.superEnum;

import br.com.testeJava.entity.enuns.superEnum.exception.NotImplementedValidatorException;
import br.com.testeJava.entity.enuns.superEnum.mocks.UmMockQualquer;
import br.com.testeJava.entity.enuns.superEnum.mocks.UsuarioMock;

public enum RoleCheckerEnum {
	HAS_ANY (){
		@Override
		public IValidationEnum[] getPermissions() {
			// TODO Auto-generated method stub
			return permissions;
		}

		@Override
		public boolean execute(UsuarioMock usuario) {
			// TODO Auto-generated method stub
			return true;
		}
	},HAS_ALL {
		@Override
		public IValidationEnum[] getPermissions() {
			// TODO Auto-generated method stub
			return permissions;
		}

		@Override
		public boolean execute(UsuarioMock usuario) {
			// TODO Auto-generated method stub
			return true;
		}
	},HAS_NOT {
		@Override
		public IValidationEnum[] getPermissions() {
			return permissions;
		}
		
		@Override
		public boolean execute(UsuarioMock usuario, UmMockQualquer mock) throws NotImplementedValidatorException {
			return true;
		}
	};
	
	protected IValidationEnum[] permissions;
	
	private RoleCheckerEnum(IValidationEnum... permissions) {
		this.permissions = permissions;
	}
	
	public boolean execute(UsuarioMock usuario) throws NotImplementedValidatorException {
		throw new NotImplementedValidatorException();
	}
	
	public boolean execute(UsuarioMock usuario, UmMockQualquer mock) throws NotImplementedValidatorException {
		throw new NotImplementedValidatorException();
	}
	
	protected abstract IValidationEnum[] getPermissions();
	
	public RoleCheckerEnum permissions(IValidationEnum... permissions) {
		this.permissions = permissions;
		return this;
	}
}
