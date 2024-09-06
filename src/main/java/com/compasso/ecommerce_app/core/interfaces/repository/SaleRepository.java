package com.compasso.ecommerce_app.core.interfaces.repository;

import com.compasso.ecommerce_app.app.dto.report.ReportDTO;
import com.compasso.ecommerce_app.core.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    public default List<Sale> findByInvoive(String invoive, List<Sale> list){

        List<Sale> listFound = new ArrayList<>();

        for (Sale sale : list) {
            if(sale.getInvoice().equals(invoive)) {
                listFound.add(sale);
            }
        }

        return listFound;

    }

    /*@Query(value="\r\n"
            + "select \r\n"
            + "name as pn,\r\n"
            + "Sum(MOVIMENTACAO_QUANTIDADE_COMPRA ) as quantidadeVendida,\r\n"
            + "Sum(MOVIMENTACAO_QUANTIDADE_COMPRA ) * PRODUTO_VALOR_UNITARIO  as valorTotal,\r\n"
            + "from Sale s\r\n"
            + "inner join Product pr on (m.PRODUTO_ID = pr.PRODUTO_CD_ID)\r\n"
            + "group by PRODUTO_ID \r\n"
            + "order by quantidadeVendida desc\r\n"
            + "limit 5", nativeQuery = true)
    List<ReportDTO> amountSale();*/

}