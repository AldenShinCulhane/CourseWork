Public Class Form1
    Private Sub Form1_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Dim pencil As Image = My.Resources.draw
        Dim trash As Image = My.Resources.trash

        InventoryDataGrid.Rows.Add("Milk", "2024-11-10", 1)
        InventoryDataGrid.Rows.Add("Eggs", "2024-11-15", 2)
        InventoryDataGrid.Rows.Add("Butter", "2025-02-01", 1)
        InventoryDataGrid.Rows.Add("Cheddar Cheese", "2024-12-01", 1)
        InventoryDataGrid.Rows.Add("Carrots", "2024-11-20", 5)
        InventoryDataGrid.Rows.Add("Spinach", "2024-11-12", 1)
        InventoryDataGrid.Rows.Add("Yogurt", "2024-11-18", 4)
        InventoryDataGrid.Rows.Add("Chicken Breast", "2024-11-14", 2)
        InventoryDataGrid.Rows.Add("Broccoli", "2024-11-16", 1)
        InventoryDataGrid.Rows.Add("Apples", "2024-11-20", 4)
        InventoryDataGrid.Rows.Add("Orange Juice", "2024-11-25", 1)
        InventoryDataGrid.Rows.Add("Bell Peppers", "2024-11-13", 3)
        InventoryDataGrid.Rows.Add("Ham", "2024-11-19", 1)
        InventoryDataGrid.Rows.Add("Pasta", "2024-11-08", 1)
        InventoryDataGrid.Rows.Add("Sour Cream", "2024-12-05", 1)

        HomeDataGridInventory.Rows.Add("Milk", "2024-11-10", 1)
        HomeDataGridInventory.Rows.Add("Eggs", "2024-11-15", 2)
        HomeDataGridInventory.Rows.Add("Butter", "2025-02-01", 1)
        HomeDataGridInventory.Rows.Add("Cheddar Cheese", "2024-12-01", 1)
        HomeDataGridInventory.Rows.Add("Carrots", "2024-11-20", 5)

        HomeDataGridNotification.Rows.Add("Milk Spoiled", "7:21PM 2024-10-05")
        HomeDataGridNotification.Rows.Add("Eggs Expiring Soon", "6:45AM 2024-10-04")
        HomeDataGridNotification.Rows.Add("Butter Running Low", "3:15PM 2024-10-03")
        HomeDataGridNotification.Rows.Add("Carrots Near Expiration", "11:30AM 2024-10-02")
        HomeDataGridNotification.Rows.Add("Yogurt Spoiled", "9:05AM 2024-10-01")

        ListDataGrid.Rows.Add("Weekly Groceries", "Nathan")
        ListDataGrid.Rows.Add("Frequently Bought", "Alden")
        ListDataGrid.Rows.Add("Snacks", "Abdullah")
        ListDataGrid.Rows.Add("Birthday Party", "Jas")

        HomeDataGridLists.Rows.Add("Weekly Groceries")
        HomeDataGridLists.Rows.Add("Frequently Bought")
        HomeDataGridLists.Rows.Add("Birthday Party")

        HomeDataGridRecipes.Rows.Add("Grilled Cheese")
        HomeDataGridRecipes.Rows.Add("Tomato Soup")
        HomeDataGridRecipes.Rows.Add("Beef Wellington")
        HomeDataGridRecipes.Rows.Add("Apple Pie")

    End Sub
    Private Sub InventoryGroup_Click(sender As Object, e As EventArgs) Handles InventoryGroup.Click
        TabControl.SelectedTab = TabControl.TabPages(1)
    End Sub

    Private Sub NotificationGroup_Click(sender As Object, e As EventArgs) Handles NotificationGroup.Click
        TabControl.SelectedTab = TabControl.TabPages(5)
    End Sub

    Private Sub ListGroup_Click(sender As Object, e As EventArgs) Handles ListGroup.Click
        TabControl.SelectedTab = TabControl.TabPages(2)
    End Sub

    Private Sub RecipeGroup_Click(sender As Object, e As EventArgs) Handles RecipeGroup.Click
        TabControl.SelectedTab = TabControl.TabPages(3)
    End Sub

    Private editingRowIndex As Integer = -1

    Private Sub InventoryDataGrid_CellContentClick(sender As Object, e As DataGridViewCellEventArgs) Handles InventoryDataGrid.CellContentClick
        If e.RowIndex >= 0 Then
            If InventoryDataGrid.Columns(e.ColumnIndex).Name = "Edit" Then
                If editingRowIndex = e.RowIndex Then
                    ToggleRowEditMode(e.RowIndex, False)
                    editingRowIndex = -1
                Else
                    If editingRowIndex >= 0 Then
                        ToggleRowEditMode(editingRowIndex, False)
                    End If

                    ToggleRowEditMode(e.RowIndex, True)
                    editingRowIndex = e.RowIndex
                End If
            End If

            If InventoryDataGrid.Columns(e.ColumnIndex).Name = "Delete" Then
                InventoryDataGrid.Rows.RemoveAt(e.RowIndex)
                If e.RowIndex = editingRowIndex Then
                    editingRowIndex = -1
                End If
            End If
        End If
    End Sub

    Private Sub ToggleRowEditMode(rowIndex As Integer, enableEdit As Boolean)
        For Each row As DataGridViewRow In InventoryDataGrid.Rows
            For Each cell As DataGridViewCell In row.Cells
                If InventoryDataGrid.Columns(cell.ColumnIndex).Name <> "Edit" AndAlso
                   InventoryDataGrid.Columns(cell.ColumnIndex).Name <> "Delete" Then
                    If row.Index = rowIndex Then
                        cell.ReadOnly = Not enableEdit
                        cell.Style.ForeColor = If(enableEdit, Color.Blue, Color.Black)
                    Else
                        cell.ReadOnly = True
                        cell.Style.ForeColor = Color.Black
                    End If
                End If
            Next
        Next

        If enableEdit Then
            InventoryDataGrid.BeginEdit(True)
        End If
    End Sub

    Private Sub HomeDataGridInventory_MouseClick(sender As Object, e As MouseEventArgs) Handles HomeDataGridInventory.MouseClick
        TabControl.SelectedTab = TabControl.TabPages(1)
    End Sub

    Private Sub HomeDataGridNotification_MouseClick(sender As Object, e As MouseEventArgs) Handles HomeDataGridNotification.MouseClick
        TabControl.SelectedTab = TabControl.TabPages(5)
    End Sub

    Private Sub HomeDataGridList_MouseClick(sender As Object, e As MouseEventArgs) Handles HomeDataGridLists.MouseClick
        TabControl.SelectedTab = TabControl.TabPages(2)
    End Sub

    Private Sub HomeDataGridRecipe_MouseClick(sender As Object, e As MouseEventArgs) Handles HomeDataGridRecipes.MouseClick
        TabControl.SelectedTab = TabControl.TabPages(3)
    End Sub

End Class
