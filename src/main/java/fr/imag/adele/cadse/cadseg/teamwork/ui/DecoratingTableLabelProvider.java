package fr.imag.adele.cadse.cadseg.teamwork.ui;

import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class DecoratingTableLabelProvider extends DecoratingLabelProvider implements
		ITableLabelProvider {

	private ITableLabelProvider _tabLabelProvider;

	public DecoratingTableLabelProvider(ILabelProvider provider,
			ILabelDecorator decorator) {
		super(provider, decorator);
		_tabLabelProvider = (ITableLabelProvider) provider;
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return _tabLabelProvider.getColumnImage(element, columnIndex);
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		return _tabLabelProvider.getColumnText(element, columnIndex);
	}

}
