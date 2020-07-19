<template>
  <!-- Footer -->
  <mdb-footer color="indigo" class="page-footer font-small pt-4">
    <mdb-container class="text-left">
      <mdb-row>
          <h5 class="title">LEGENDARY News</h5>
          <p>A leading Journal based in Cairo,Egypt. powered by an enthusiastic authors,
            artists and businessmen who are aware of the continuous changes of people taste and technological challenges.
            <br>We always keen on providing the naked truth for the community.</p>
      </mdb-row>
    </mdb-container>
    <div class="footer-copyright text-center py-3">
      <mdb-container fluid>
        &copy; 2020 Copyright: <a href="https://www.google.com"> legendarynews.com </a>
      </mdb-container>
    </div>
  </mdb-footer>
  <!-- Footer -->
</template>

<script>
  import { mdbFooter, mdbContainer, mdbRow, mdbCol } from 'mdbvue';
  export default {
    name: 'FooterPage',
    components: {
      mdbFooter,
      mdbContainer,
      mdbRow,
      mdbCol
    }
  }
</script>

<!-- fixed-bottom -->
